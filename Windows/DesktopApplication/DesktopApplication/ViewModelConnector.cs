

using DesktopApplication.ViewModel;
using System.ComponentModel;
using System.Windows.Input;

namespace DesktopApplication
{
    class ViewModelConnector : BaseViewModel
    {
        public User myUser = new User("Jan", "password", "mail.com");
        public ICommand ExpandCommand { get; set; }
        public ViewModelConnector()
        {
            this.ExpandCommand = new RelayCommand(Expand);
        }

        private void Expand()
        {
            myUser.ShowInfo();
        }

        public override string ToString()
        {
            return "Hello World";
        }
    }
}
