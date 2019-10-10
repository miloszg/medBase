

using System.ComponentModel;
using System.Windows.Input;

namespace DesktopApplication
{
    public class ViewModelConnector : BaseViewModel
    {
        public ICommand changePageCommand;
        public ApplicationPage CurrentPage { get; set; } = ApplicationPage.Login;
        public ViewModelConnector()
        {
            changePageCommand = new RelayCommand(() => changePage());
        }

        public void changePage()
        {
            this.CurrentPage = ApplicationPage.SignUp;
        }

    }
}
