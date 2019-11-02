using System.Windows.Controls;

namespace DesktopApplication
{
    /// <summary>
    /// Interaction logic for MyMedsPage.xaml
    /// </summary>
    public partial class MedsPage : Page
    {
        public MedsPage()
        {
            InitializeComponent();
            this.DataContext = new MedsViewModel();
        }
    }
}
