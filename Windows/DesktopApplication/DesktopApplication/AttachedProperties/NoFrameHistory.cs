using System.Windows;
using System.Windows.Controls;

namespace DesktopApplication
{
    public class NoFrameHistory : BaseAttachedProperty<NoFrameHistory, bool>
    {
        public override void OnValueChanged(DependencyObject sender, DependencyPropertyChangedEventArgs e)
        {
            var frame = (sender as Frame);

            if(frame == null)
            {
                return;
            }

            frame.NavigationUIVisibility = System.Windows.Navigation.NavigationUIVisibility.Hidden;

            // Clear history on navigate
            frame.Navigated += (ss, ee) =>((Frame)ss).NavigationService.RemoveBackEntry();
        }
    }
}
