

using System.ComponentModel;

namespace DesktopApplication
{
    class ViewModelConnector : INotifyPropertyChanged
    {
        public event PropertyChangedEventHandler PropertyChanged = (sender, e) => { };

        public string Test { get; set; } = "My property";

        public override string ToString()
        {
            return "Hello World";
        }
    }
}
