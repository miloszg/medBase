using System;
using System.Windows.Input;

namespace DesktopApplication
{
    public class RelayCommandWithParameters : ICommand
    {
        private readonly Action<object> action;

        public RelayCommandWithParameters(Action<object> action)
        {
            this.action = action;
        }

        public event EventHandler CanExecuteChanged = (sender, e) => { };

        public bool CanExecute(object parameter)
        {
            return true;
        }

        public void Execute(object parameter)
        {
            this.action(parameter);
        }
    }
}
