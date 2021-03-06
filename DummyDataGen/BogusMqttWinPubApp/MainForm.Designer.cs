﻿namespace BogusMqttWinPubApp
{
    partial class MainForm
    {
        /// <summary>
        /// 필수 디자이너 변수입니다.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// 사용 중인 모든 리소스를 정리합니다.
        /// </summary>
        /// <param name="disposing">관리되는 리소스를 삭제해야 하면 true이고, 그렇지 않으면 false입니다.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form 디자이너에서 생성한 코드

        /// <summary>
        /// 디자이너 지원에 필요한 메서드입니다. 
        /// 이 메서드의 내용을 코드 편집기로 수정하지 마세요.
        /// </summary>
        private void InitializeComponent()
        {
            this.label1 = new System.Windows.Forms.Label();
            this.TxtBrokerIP = new System.Windows.Forms.TextBox();
            this.BtnConnect = new System.Windows.Forms.Button();
            this.RtbLog = new System.Windows.Forms.RichTextBox();
            this.SuspendLayout();
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(24, 64);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(96, 12);
            this.label1.TabIndex = 0;
            this.label1.Text = "MQTT Broker IP";
            // 
            // TxtBrokerIP
            // 
            this.TxtBrokerIP.Location = new System.Drawing.Point(126, 61);
            this.TxtBrokerIP.Name = "TxtBrokerIP";
            this.TxtBrokerIP.Size = new System.Drawing.Size(570, 21);
            this.TxtBrokerIP.TabIndex = 1;
            this.TxtBrokerIP.Text = "localhost";
            // 
            // BtnConnect
            // 
            this.BtnConnect.Location = new System.Drawing.Point(702, 61);
            this.BtnConnect.Name = "BtnConnect";
            this.BtnConnect.Size = new System.Drawing.Size(75, 23);
            this.BtnConnect.TabIndex = 2;
            this.BtnConnect.Text = "Connect";
            this.BtnConnect.UseVisualStyleBackColor = true;
            this.BtnConnect.Click += new System.EventHandler(this.BtnConnect_Click);
            // 
            // RtbLog
            // 
            this.RtbLog.Location = new System.Drawing.Point(26, 94);
            this.RtbLog.Name = "RtbLog";
            this.RtbLog.Size = new System.Drawing.Size(751, 352);
            this.RtbLog.TabIndex = 3;
            this.RtbLog.Text = "";
            // 
            // MainForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(7F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(800, 450);
            this.Controls.Add(this.RtbLog);
            this.Controls.Add(this.BtnConnect);
            this.Controls.Add(this.TxtBrokerIP);
            this.Controls.Add(this.label1);
            this.Name = "MainForm";
            this.Resizable = false;
            this.Text = "MQTT Fake Publisher";
            this.FormClosing += new System.Windows.Forms.FormClosingEventHandler(this.MainForm_FormClosing);
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.TextBox TxtBrokerIP;
        private System.Windows.Forms.Button BtnConnect;
        private System.Windows.Forms.RichTextBox RtbLog;
    }
}

