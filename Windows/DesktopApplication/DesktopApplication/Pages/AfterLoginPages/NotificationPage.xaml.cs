using System.Windows.Controls;

namespace DesktopApplication
{
    /// <summary>
    /// Interaction logic for NotificationPage.xaml
    /// </summary>
    public partial class NotificationPage : Page
    {
        public NotificationPage()
        {
            InitializeComponent();
            this.DataContext = IoC.Get<ApplicationWindowViewModel>();
        }
    }
}
