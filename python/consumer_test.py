'''
Created on 2013-3-20

@author: moon
'''

import Ice, sys, traceback, time, json
Ice.loadSlice('KafkaConsumerClient.ice')
from bfd.kafkaconsumerclient import KafkaConsumerClientManagerPrx

if __name__ == '__main__':
    if len(sys.argv) < 3:
        print "wrong args!\nUSAGE: python consumer_test.py topic_name group_name"
        sys.exit(-1)
    status = 0
    ic = None

    p = Ice.createProperties()
    p.setProperty("Ice.Default.Locator", "bfdcloud/Locator:default -h 192.168.50.16 -p 7893")
    p.setProperty("Ice.Default.LocatorCacheTimeout", "1000")
    id = Ice.InitializationData()
    id.properties = p

    try:
        ic = Ice.initialize(id)
        base = ic.stringToProxy("M@KafkaConsumerProxy")
        print "base: ", base

        kfk = KafkaConsumerClientManagerPrx.checkedCast(base)
        # kfk.setConsumeMethod(sys.argv[2],1) # 0-> largest, consumer from tail
        print "kinit kafka"
        '''
        i = 0
        while i < 10:
            lst = kfk.getArray(sys.argv[1], sys.argv[2], 0, 1)
            print "end getArray"
            for m in lst:
                print m
            i += 1
        '''
        print kfk.getStatusInfo()
        # kfk.closeAndDelete(sys.argv[1], sys.argv[2], 0)
        kfk.close(sys.argv[1], sys.argv[2], 0)
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

