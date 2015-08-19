#ifndef _KAFKACONSUMERCLIENT_ICE_
#define _KAFKACONSUMERCLIENT_ICE_
module bfd{
module kafkaconsumerclient{

	class RetInfo{
		string message;
		int ret;
	};
	sequence<RetInfo> RetInfoSeq;
	interface KafkaConsumerClientManager{
		long getUserID();
		void close(string topic, string group, long userid);
		void closeAndDelete(string topic, string group, long userid);
		int setConsumeMethod(string group, int method);
		string getStatusInfo();
		RetInfo get(string topic, string group, long userid);
		RetInfo getFromBegin(string topic, string group, long userid);
		RetInfo getPeriod(string topic, string group, string date, long userid);
		RetInfo getPeriodFromBegin(string topic, string group, string date, long userid);
		RetInfoSeq getArray(string topic, string group, long userid, int seqlen);
		RetInfoSeq getArrayFromBegin(string topic, string group, long userid, int seqlen);
		RetInfoSeq getArrayPeriod(string topic, string group, string date, long userid, int seqlen);
		RetInfoSeq getArrayPeriodFromBegin(string topic,string group,string date, long userid, int seqlen);
		RetInfo getBlock(string topic, string group, long userid);
		RetInfo getFromBeginBlock(string topic, string group, long userid);
		RetInfoSeq getArrayBlock(string topic, string group, long userid, int seqlen);
		RetInfoSeq getArrayFromBeginBlock(string topic, string group, long userid, int seqlen);
		void commitoffset(string topic, string group, long userid);
		RetInfo hadoopGet(string topic, string group, long userid);
		RetInfoSeq hadoopGetTopicList(string prefix, string password);
	};
};
};

#endif
