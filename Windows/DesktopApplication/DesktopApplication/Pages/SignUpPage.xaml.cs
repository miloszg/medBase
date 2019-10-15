using System.Security;
using System.Windows.Controls;

namespace DesktopApplication
{
    /// <summary>
    /// Interaction logic for SignUpPage.xaml
    /// </summary>
    public partial class SignUpPage : Page, IPassword
    {
        public SignUpPage()
        {
            InitializeComponent();
            this.UsernameTextBox.Focus();
            this.DataContext = new SignUpViewModel();
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
                return this.RepeatPasswordTextBox.SecurePassword;
            }
        }
    }
}
