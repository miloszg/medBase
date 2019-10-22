using System.Windows.Controls;

namespace DesktopApplication
{
    /// <summary>
    /// Interaction logic for SearchForMedsPage.xaml
    /// </summary>
    public partial class PatientPage : Page
    {
        public PatientPage()
        {
            InitializeComponent();
            this.DataContext = new PatientPageViewModel();
        }
    }
}
