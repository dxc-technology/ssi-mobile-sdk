git clone https://github.com/hyperledger/aries-cloudagent-python

PORTS="8030:8030 8031:8031" ./scripts/run_docker start \
   --endpoint ws://192.168.0.103:8030 \
   --label alice.agent \
   --inbound-transport ws 0.0.0.0 8030 \
   --outbound-transport ws \
   --admin 0.0.0.0 8031 \
   --admin-insecure-mode \
   --wallet-type indy \
   --wallet-name alice.agent420695 \
   --wallet-key alice.agent420695 \
   --preserve-exchange-records \
   --wallet-local-did \
   --auto-provision \
   --genesis-url http://dev.greenlight.bcovrin.vonx.io/genesis \
   --trace-target log \
   --trace-tag acapy.events \
   --trace-label alice.agent.trace \
   --auto-ping-connection \
   --log-level DEBUG \
   --trace-target log \
   --trace-tag acapy.events \
   --trace-label alice.agent.trace \
   --recreate-wallet \
   --auto-accept-invites \
   --auto-accept-requests \
   --auto-store-credential \
   --emit-new-didcomm-prefix