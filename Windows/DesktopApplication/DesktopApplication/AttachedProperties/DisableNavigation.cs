using System.Windows;
using System.Windows.Controls;
using System.Windows.Navigation;

namespace DesktopApplication
{
    public static class DisableNavigation
    {
        public static bool GetDisable(DependencyObject o)
        {
            return (bool)o.GetValue(DisableProperty);
        }
        public static void SetDisable(DependencyObject o, bool value)
        {
            o.SetValue(DisableProperty, value);
        }

        public static readonly DependencyProperty DisableProperty =
            DependencyProperty.RegisterAttached("Disable", typeof(bool), typeof(DisableNavigation),
                                                new PropertyMetadata(false, DisableChanged));

        public static void DisableChanged(object sender, DependencyPropertyChangedEventArgs e)
        {
            var frame = (Frame)sender;
            frame.Navigated += (ss, ee) => ((Frame)ss).NavigationService.RemoveBackEntry();
            frame.NavigationUIVisibility = NavigationUIVisibility.Hidden;
        }
    }
}