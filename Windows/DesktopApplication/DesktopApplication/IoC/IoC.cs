using Ninject;

namespace DesktopApplication
{
    public static class IoC
    {
        public static IKernel Kernel { get; set; } = new StandardKernel();
    
        public static void Setup()
        {
            BindViewModels();
        }

        public static T Get<T>()
        {
            return Kernel.Get<T>();
        }

        private static void BindViewModels()
        {
            // Bind to a single instance of ViewModelConnector
            Kernel.Bind<MainWindowViewModel>().ToConstant(new MainWindowViewModel());
            Kernel.Bind<ApplicationWindowViewModel>().ToConstant(new ApplicationWindowViewModel());
        }
    }
}
