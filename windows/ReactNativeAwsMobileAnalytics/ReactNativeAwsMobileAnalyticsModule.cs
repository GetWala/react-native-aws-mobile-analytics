using ReactNative.Bridge;
using System;
using System.Collections.Generic;
using Windows.ApplicationModel.Core;
using Windows.UI.Core;

namespace Com.Reactlibrary.ReactNativeAwsMobileAnalytics
{
    /// <summary>
    /// A module that allows JS to share data.
    /// </summary>
    class ReactNativeAwsMobileAnalyticsModule : NativeModuleBase
    {
        /// <summary>
        /// Instantiates the <see cref="ReactNativeAwsMobileAnalyticsModule"/>.
        /// </summary>
        internal ReactNativeAwsMobileAnalyticsModule()
        {

        }

        /// <summary>
        /// The name of the native module.
        /// </summary>
        public override string Name
        {
            get
            {
                return "ReactNativeAwsMobileAnalytics";
            }
        }
    }
}
