using AMTest.Base;
using MahApps.Metro.Controls;
using System.IO.Ports;
using System;
using System.Collections.Generic;
using System.Windows.Forms;
using MySql.Data.MySqlClient;
using AMTest.Views;

namespace AMTest
{
    /// <summary>
    /// MainWindow.xaml에 대한 상호 작용 논리
    /// </summary>
    public partial class MainWindow : MetroWindow
    {
        SerialPort serial;
        private short maxPhotoVal = 1023;
        List<SensorData> photoDatas = new List<SensorData>();

        string strConnString = "Server=localhost;Port=3306;" +
            "Database=iot_sensordata;Uid=root;Pwd=0000"; // 비밀 번호 변경

        public bool IsSimulation { get; set; }

        Timer timer = new Timer(); // 현재 시간
        Random rand = new Random(); // 랜덤

        // 차트 배열
        List<int> x = new List<int>();
        List<int> y = new List<int>();

        public MainWindow()
        {
            InitializeComponent();
            InitControls();
        }

        private void InitControls() // 포트 연결, 콤보 박스
        {
            foreach (var item in SerialPort.GetPortNames())
            {
                CboSerialPort.Items.Add(item);
            }
            CboSerialPort.Text = "Select Port";

            PgbPhotoRegistor.Minimum = 0;
            PgbPhotoRegistor.Maximum = maxPhotoVal;

            BtnConnect.IsEnabled = BtnDisconnect.IsEnabled = false;
        }

        private void CboSerialPort_SelectionChanged(object sender, System.Windows.Controls.SelectionChangedEventArgs e)
        {
            var portName = CboSerialPort.SelectedItem.ToString();
            serial = new SerialPort(portName);
            serial.DataReceived += Serial_DataReceived;

            BtnConnect.IsEnabled = true; // 포트에 연결되면 Connect버튼 활성화
        }

        private void Serial_DataReceived(object sender, SerialDataReceivedEventArgs e)
        {
            string sVal = serial.ReadLine();
            this.BeginInvoke(new Action(delegate { DisplayValue(sVal); }));
        }


        private void DisplayValue(string sVal) 
        {
            try
            {
                ushort v = ushort.Parse(sVal);
                if (v < 0 || v > maxPhotoVal)
                    return;

                SensorData data = new SensorData(DateTime.Now, v);
                photoDatas.Add(data);
                InsertDataToDB(data);

                TxtSensorCount.Text = photoDatas.Count.ToString();
                PgbPhotoRegistor.Value = v;
                LblPhotoRegistor.Content = v.ToString();

                string item = $"{DateTime.Now.ToString("yyyy-MM-dd hh:mm:ss")}\t{v}";

                RtbLog.AppendText($"{item}\n"); // 연결 시간 item
                RtbLog.ScrollToEnd();

                x.Add(photoDatas.Count);
                y.Add(v);
                ChtSensorValues.Plot(x, y);

                if (IsSimulation == false)
                    BtnPortValue.Content = $"{serial.PortName}\n{sVal}";
                else
                    BtnPortValue.Content = $"{sVal}";
                
            }
            catch (Exception ex)
            {
                RtbLog.AppendText($"Error : {ex.Message}\n");
                RtbLog.ScrollToEnd();
            }
        }
        
        private void InsertDataToDB(SensorData data) // DB 삽입
        {
            string strQuery = "INSERT INTO sensordatatbl " +
                " (Date, Value) " +
                " VALUES " +
                " (@Date, @Value) ";

            using (MySqlConnection conn = new MySqlConnection(strConnString))
            {
                conn.Open();
                MySqlCommand cmd = new MySqlCommand(strQuery, conn);
                MySqlParameter paramDate = new MySqlParameter("@Date", MySqlDbType.DateTime)
                {
                    Value = data.Date
                };
                cmd.Parameters.Add(paramDate);
                MySqlParameter paramValue = new MySqlParameter("@Value", MySqlDbType.Int32)
                {
                    Value = data.Value
                };
                cmd.Parameters.Add(paramValue);
                cmd.ExecuteNonQuery();
            }
        } 

        private void BtnConnect_Click(object sender, EventArgs e) // Connect 버튼 클릭 시
        {
            if(serial != null)
                serial.Open();
            LblConnectionTime.Content = $"연결시간 : {DateTime.Now.ToString("yyyy-MM-dd hh:mm:ss")}";
            BtnConnect.IsEnabled = false;
            BtnDisconnect.IsEnabled = true;
        }

        private void BtnDisconnect_Click(object sender, EventArgs e) // Disconnect 버튼 클릭 시
        {
            if (serial != null)
                serial.Close();
            BtnConnect.IsEnabled = true;
            BtnDisconnect.IsEnabled = false;
        }

        private void BtnViewAll_Click(object sender, EventArgs e) // ViewAll 버튼 클릭 시
        {
            /*
            ChtSensorValues.ChartAreas[0].AxisX.Minimum = 0;
            ChtSensorValues.ChartAreas[0].AxisX.Maximum = photoDatas.Count;
            ChtSensorValues.ChartAreas[0].AxisX.ScaleView.Zoom(0, photoDatas.Count);
            ChtSensorValues.ChartAreas[0].AxisX.Interval = photoDatas.Count / 4;
            */
        }

        private void BtnZoom_Click(object sender, EventArgs e) // Zoom 버튼 클릭 시
        {
            /*
            ChtSensorValues.ChartAreas[0].AxisX.Minimum = 0;
            ChtSensorValues.ChartAreas[0].AxisX.Maximum = photoDatas.Count;
            ChtSensorValues.ChartAreas[0].AxisX.ScaleView.Zoom(photoDatas.Count - xCount, photoDatas.Count);
            ChtSensorValues.ChartAreas[0].AxisX.Interval = photoDatas.Count / 4;
            */
        }

        private void MenuSubItemExit_Click(object sender, EventArgs e) // 끝내기
        {
            Close();
        }

        private void MenuSubItemStart_Click(object sender, EventArgs e) // 시뮬레이션 시작
        {
            IsSimulation = true;
            timer.Interval = 1000;
            timer.Tick += Timer_Tick;
            timer.Start();

            // serial통신 끊기
            BtnDisconnect_Click(sender, e);
        }

        private void Timer_Tick(object sender, EventArgs e) // 난수 생성
        {
            ushort value = (ushort)rand.Next(1, 1024);
            DisplayValue(value.ToString());
        }

        private void MenuSubItemStop_Click(object sender, EventArgs e) // 시뮬레이션 중단
        {
            timer.Stop();
            IsSimulation = false;

            // serial 통신 재시작
            BtnConnect_Click(sender, e);
        }

        private void MenuSubItemInfo_Click(object sender, EventArgs e) // 도움말 정보 클릭
        {
            ThisProgramForm form = new ThisProgramForm();
            form.ShowDialog();
        }
    }
}