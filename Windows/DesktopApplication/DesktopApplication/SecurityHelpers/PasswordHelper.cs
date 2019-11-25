using System;
using System.Runtime.InteropServices;
using System.Security;

namespace DesktopApplication
{
    static public class PasswordHelper
    {
        static public string ConvertToUnsecureString(SecureString securePassword)
        {
            if(securePassword == null)
            {
                return string.Empty;
            }

            IntPtr unmanagedString = IntPtr.Zero;
            try
            {
                unmanagedString = Marshal.SecureStringToGlobalAllocUnicode(securePassword);
                return Marshal.PtrToStringUni(unmanagedString);
            }
            finally
            {
                Marshal.ZeroFreeGlobalAllocUnicode(unmanagedString);
            }
        }
        
        static public SecureString ConvertToSecureString(string unsecurePassword)
        {
            if (unsecurePassword == null)
            {
                throw new ArgumentNullException("password");
            }
            unsafe
            {
                fixed (char * passwordChars = unsecurePassword)
                {
                    var securePassword = new SecureString(passwordChars, unsecurePassword.Length);
                    securePassword.MakeReadOnly();
                    return securePassword;
                }
            }
        }

    }
}
