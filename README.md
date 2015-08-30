# NoVpnMonitor

Removes the "Network may be monitored" message in Android's quick toggles panel 
when using an application-provided VPN connection. Relevant code can be 
found [here](https://github.com/android/platform_frameworks_base/blob/master/packages/SystemUI/src/com/android/systemui/qs/QSFooter.java).

## Requirements

- A rooted Android phone running 5.0 Lollipop or above
- [Xposed framework for Lollipop](http://forum.xda-developers.com/showthread.php?t=3034811)

## License

Distributed under the [MIT License](http://opensource.org/licenses/MIT).
