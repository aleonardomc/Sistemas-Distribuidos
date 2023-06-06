#sudo apt-get install python3-libtorrent
# magnet:?xt=urn:btih:4MR6HU7SIHXAXQQFXFJTNLTYSREDR5EI&tr=http://tracker.vodo.net:6970/announce
# https://www.libtorrent.org/python_binding.html

import libtorrent as lt
import time
import datetime

link = input("Ingresa el archivo magnent: ")

ses = lt.session()
ses.listen_on(6881, 6891)
params = {
    'save_path': '/home/leo/Escritorio/SD/Bittorrent/Archivos'}
"""
        'storage_mode': lt.storage_mode_t(2),
        'paused': True,
        'auto_managed': True,
        'duplicate_is_error': True}
"""

print(link)

handle = lt.add_magnet_uri(ses, link, params)
ses.start_dht()

begin = time.time()
print(datetime.datetime.now())

print ('Downloading Metadata...')
while (not handle.has_metadata()):
    time.sleep(1)
print ('Got Metadata, Starting Torrent Download...')

print("Starting", handle.name())

while (handle.status().state != lt.torrent_status.seeding):
    s = handle.status()
    state_str = ['queued', 'checking', 'downloading metadata', \
            'downloading', 'finished', 'seeding', 'allocating']
    print ('%.2f%% complete (down: %.1f kb/s up: %.1f kB/s peers: %d) %s ' % \
            (s.progress * 100, s.download_rate / 1000, s.upload_rate / 1000, \
            s.num_peers, state_str[s.state]))
    time.sleep(5)

end = time.time()
print(handle.name(), "COMPLETE")

print("Elapsed Time: ",int((end-begin)//60),"min :", int((end-begin)%60), "sec")
print(datetime.datetime.now())  
