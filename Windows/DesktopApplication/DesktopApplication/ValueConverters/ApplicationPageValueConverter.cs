using System;
using System.Diagnostics;
using System.Globalization;

namespace DesktopApplication
{
    public class ApplicationPageValueConverter : BaseValueConverter<ApplicationPageValueConverter>
    {
        public override object Convert(object value, Type targetType, object parameter, CultureInfo culture)
        {
            switch ((ApplicationPage)value)
            {
                case ApplicationPage.Login:
                    return new LoginPage();
                case ApplicationPage.SignUp:
                    return new SignUpPage();
                case ApplicationPage.MainMenu:
                    return new MainMenuPage();
                case ApplicationPage.Meds:
                    return new MedsPage();
                case ApplicationPage.Patient:
                    return new PatientPage();
                case ApplicationPage.GenerateReceipt:
                    return new GenerateReceiptPage();
                case ApplicationPage.SubmitPatient:
                    return new SubmitPatientPage();
                default:
                    Debugger.Break();
                    break;
            }
            return new SignUpPage();
        }

        public override object ConvertBack(object value, Type targetType, object parameter, CultureInfo culture)
        {
            throw new NotImplementedException();
        }
    }
}
