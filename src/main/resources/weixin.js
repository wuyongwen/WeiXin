{
	root: {
		type: "com.chn.wx.MessageHandler",
		singleton: true,
		args: [], 
		fields: {
			proxy: {
				type: "com.chn.wx.listener.impl.process.AsyncThreadMode", 
				//class: "com.chn.wx.listener.impl.process.SyncThreadMode",
				args: [ {
					type: "com.chn.wx.MessageHandler$PackageClassProvider", 
					args: ["com.chn.wx.listener.impl.service|com.chn.custom.service"]
				}]
			}, 
		}
	}
}