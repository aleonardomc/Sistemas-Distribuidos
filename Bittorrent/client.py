#sudo apt-get install python3-libtorrent
# magnet:?xt=urn:btih:4MR6HU7SIHXAXQQFXFJTNLTYSREDR5EI&tr=http://tracker.vodo.net:6970/announce
# https://www.libtorrent.org/python_binding.html

import libtorrent as lt
import threading
import argparse
import sys

def download_torrent(link, save_path):
    ses = lt.session()
    ses.listen_on(6881, 6891)
    params = {
        'save_path': save_path
    }
    handle = lt.add_magnet_uri(ses, link, params)
    ses.start_dht()

    print(f"Starting download for: {handle.name()}")

    while handle.status().state != lt.torrent_status.seeding:
        s = handle.status()
        state_str = ['queued', 'checking', 'downloading metadata', 'downloading', 'finished', 'seeding', 'allocating']
        print(f"Progress: {s.progress * 100:.2f}%  Down: {s.download_rate / 1000:.1f} kB/s  Up: {s.upload_rate / 1000:.1f} kB/s  Peers: {s.num_peers}  State: {state_str[s.state]}")
        threading.Event().wait(5)  # Esperar 5 segundos antes de verificar el estado nuevamente

    print(f"Download completed for: {handle.name()}")

# Configurar el argumento de línea de comandos
parser = argparse.ArgumentParser(description='Torrent Client')
parser.add_argument('save_path', type=str, help='Directorio de descarga')

# Obtener el directorio de descarga del argumento de línea de comandos
args = parser.parse_args(sys.argv[1:])
save_path = args.save_path

# Solicitar los enlaces magnet al usuario
links = []
num_torrents = int(input("Ingrese la cantidad de torrents a descargar: "))
for i in range(num_torrents):
    link = input(f"Ingrese el enlace magnet #{i+1}: ")
    links.append(link)

# Crear hilos para descargar los torrents en paralelo
threads = []
for link in links:
    thread = threading.Thread(target=download_torrent, args=(link, save_path))
    thread.start()
    threads.append(thread)

# Esperar a que todos los hilos terminen
for thread in threads:
    thread.join()
