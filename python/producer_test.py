'''
Created on 2013-3-20

@author: moon
'''

import Ice, sys, traceback, time, json
Ice.loadSlice('KafkaProducerClient.ice')
from bfd.kafkaproducerclient import KafkaProducerClientManagerPrx

if __name__ == '__main__':
    if len(sys.argv) < 3:
        print "wrong args!\nUSAGE: python producer_test.py topic_name message_str"
        sys.exit(-1)
    
    status = 0
    ic = None

    p = Ice.createProperties()
    p.setProperty("Ice.Default.Locator", "testcloud/Locator:default -h 172.18.1.53 -p 7893")
    id = Ice.InitializationData()
    id.properties = p

    try:
        ic = Ice.initialize(id)
        base = ic.stringToProxy("M@KafkaProducerProxy")

        kfk = KafkaProducerClientManagerPrx.checkedCast(base)
        userid = kfk.getUserID()
        code = kfk.send(sys.argv[1], sys.argv[2], userid)
        if code == 0:
            print "send message %s to topic %s successfully!"%(sys.argv[2], sys.argv[1])
        else :
            print "send message failed! code=" + code
        # kfk.close(sys.argv[1], 0)
    except:
        traceback.print_exc()
        status = 1
    if ic:
        try:
            ic.destroy()
        except:
            traceback.print_exc()
            status = 1
    sys.exit(status)

