using System;
using System.Collections.Generic;
using System.Linq;
using System.Security;
using System.Text;
using System.Threading.Tasks;

namespace DesktopApplication
{
    public interface IPassword
    {
        SecureString Passsword { get; }
        SecureString RepeatPassword { get; }
    }
}
