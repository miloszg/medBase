using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Input;

namespace DesktopApplication.ViewModel
{
    class SignInViewModel : BaseViewModel
    {
        public string username { get; set; }
        public string password { get; set; }
        public string mail { get; set; }

        public SignInViewModel()
        {
        }
        public void GetUsername(string _username)
        {
            username = _username;
        }

        public void GetPassword(string _password)
        {
            password = _password;
        }


        public void GetMail(string _mail)
        {
            mail = _mail;
        }

        public void Execute()
        {
            username = "username";
            password = "password";
            mail = "mail";
        }
    }
}
