using System.Security;
using System.Windows.Controls;

namespace DesktopApplication
{
    /// <summary>
    /// Interaction logic for LoginPage.xaml
    /// </summary>
    public partial class LoginPage : Page, IPassword
    {
        public LoginPage()
        {
            InitializeComponent();
            this.UsernameTextBox.Focus();
            this.DataContext = new LoginViewModel();
        }

        public SecureString Passsword
        {
            get
            {
                return this.PasswordTextBox.SecurePassword;
            }
        }

        public SecureString RepeatPassword
        {
            get
            {
                return this.PasswordTextBox.SecurePassword;
            }
        }
    }
}
