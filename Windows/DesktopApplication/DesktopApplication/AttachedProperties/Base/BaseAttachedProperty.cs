﻿using System;
using System.Windows;

namespace DesktopApplication
{
    public abstract class BaseAttachedProperty<Parent, Property>
        where Parent: new()
    {
        public event Action<DependencyObject, DependencyPropertyChangedEventArgs> ValueChanged = (sender, e) => { };
        public event Action<DependencyObject, object> ValueUpdated = (sender, e) => { };


        public static Parent Instance { get; private set; } = new Parent();

        public static readonly DependencyProperty ValueProperty = DependencyProperty.RegisterAttached(
            "Value", 
            typeof(Property),
            typeof(BaseAttachedProperty<Parent, Property>),
            new UIPropertyMetadata(
                default(Property),
                new PropertyChangedCallback(OnValuePropertyChanged),
                new CoerceValueCallback(OnValuePropertyUpdated)
                ));

        private static void OnValuePropertyChanged(DependencyObject d, DependencyPropertyChangedEventArgs e)
        {
            (Instance as BaseAttachedProperty<Parent, Property>)?.OnValueChanged(d, e);

            (Instance as BaseAttachedProperty<Parent, Property>)?.ValueChanged(d, e);
        }

        private static object OnValuePropertyUpdated(DependencyObject d, object value)
        {
            (Instance as BaseAttachedProperty<Parent, Property>)?.OnValueUpdated(d, value);

            (Instance as BaseAttachedProperty<Parent, Property>)?.ValueUpdated(d, value);

            return value;
        }

        public virtual void OnValueChanged(DependencyObject sender, DependencyPropertyChangedEventArgs e) {}

        public virtual void OnValueUpdated(DependencyObject sender, object e) { }


        public static Property GetValue(DependencyObject d)
        {
            return (Property)d.GetValue(ValueProperty);
        }


        public static void SetValue(DependencyObject d, Property value)
        {
            d.SetValue(ValueProperty, value);
        }
    }
}
