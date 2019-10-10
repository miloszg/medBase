﻿using System;
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
                case ApplicationPage.Start:
                    return new StartPage();
                case ApplicationPage.MainMenu:
                    return new MainMenuPage();
                default:
                    Debugger.Break();
                    break;
            }
            return new StartPage();
        }

        public override object ConvertBack(object value, Type targetType, object parameter, CultureInfo culture)
        {
            throw new NotImplementedException();
        }
    }
}
