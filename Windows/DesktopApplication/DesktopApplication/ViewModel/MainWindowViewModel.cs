

using System.ComponentModel;
using System.Windows.Input;

namespace DesktopApplication
{
    public class MainWindowViewModel : BaseViewModel
    {
        public ICommand changePageCommand;
        public ICommand closingCommand;
        public ApplicationPage CurrentPage { get; set; } = ApplicationPage.Login;
        public MainWindowViewModel()
        {
            this.changePageCommand = new RelayCommand(() => changePage());
        }

        public void changePage()
        {
            this.CurrentPage = ApplicationPage.SignUp;
        }

    }
}
