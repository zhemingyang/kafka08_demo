#ifndef __KAFKAPRODUCERCLIENT_ICE__
#define __KAFKAPRODUCERCLIENT_ICE__

module bfd{
	module kafkaproducerclient{
		sequence<string> messagelist;


        interface KafkaProducerClientManager{
			long getUserID();
			int send(string topic, string message, long userid);
			int mulitsend(string topic, messagelist message, long userid);
			void close(string topic, long userid);
};
};
};


#endif

