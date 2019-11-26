using System;
using System.Collections.Generic;
using System.Globalization;

namespace DesktopApplication
{
    public class MedicationDataListConverter : BaseValueConverter<MedicationDataListConverter>
    {
        public override object Convert(object value, Type targetType, object parameter, CultureInfo culture)
        {
            List<string> dataList = (List<string>)value;

            string convertedData = "";
            foreach (string data in dataList)
            {
                convertedData += $"{data},";
            }
            if(convertedData.Length > 0)
            {
                convertedData = convertedData.Remove(convertedData.Length-1);
            }
            return convertedData;
        }

        public override object ConvertBack(object value, Type targetType, object parameter, CultureInfo culture)
        {
            throw new NotImplementedException();
        }
    }
}
