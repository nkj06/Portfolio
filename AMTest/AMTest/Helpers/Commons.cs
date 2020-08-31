using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace AMTest.Helpers
{
    class Commons
    {
        internal static readonly string CONNSTRING =
            "Data source = localhost;Port=3306;Database=iot_sensordata;Uid=root;Password=0000;";
    }

    public class SensordataTbl
    {
        public static string SELECT_SENSOR = "SELECT id, " +
                                             "       Date, " +
                                             "       Value " +
                                             "  FROM iot_sensordata ";
    }
}
