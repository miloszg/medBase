﻿using Ninject;
using System.Windows;
using System.Windows.Controls;

namespace DesktopApplication
{
    /// <summary>
    /// Interaction logic for MainMenu.xaml
    /// </summary>
    public partial class MainMenuPage : Page
    {
        public MainMenuPage()
        {
            InitializeComponent();
            this.DataContext = IoC.Kernel.Get<ApplicationWindowViewModel>();
        }
    }
}
