# sudo apt-get install python3-libtorrent
# magnet:?xt=urn:btih:4MR6HU7SIHXAXQQFXFJTNLTYSREDR5EI&tr=http://tracker.vodo.net:6970/announce
# magnet:?xt=urn:btih:E5FDB6D8700F2C343E4DC95FB0CC4C04D1A6E76B&dn=Hip+Hop+Sample+Pack%3A+The+Backspacez+Vault+%5BWAV%5D&tr=http%3A%2F%2Fp4p.arenabg.com%3A1337%2Fannounce&tr=udp%3A%2F%2F47.ip-51-68-199.eu%3A6969%2Fannounce&tr=udp%3A%2F%2F9.rarbg.me%3A2780%2Fannounce&tr=udp%3A%2F%2F9.rarbg.to%3A2710%2Fannounce&tr=udp%3A%2F%2F9.rarbg.to%3A2730%2Fannounce&tr=udp%3A%2F%2F9.rarbg.to%3A2920%2Fannounce&tr=udp%3A%2F%2Fopen.stealth.si%3A80%2Fannounce&tr=udp%3A%2F%2Fopentracker.i2p.rocks%3A6969%2Fannounce&tr=udp%3A%2F%2Ftracker.coppersurfer.tk%3A6969%2Fannounce&tr=udp%3A%2F%2Ftracker.cyberia.is%3A6969%2Fannounce&tr=udp%3A%2F%2Ftracker.dler.org%3A6969%2Fannounce&tr=udp%3A%2F%2Ftracker.internetwarriors.net%3A1337%2Fannounce&tr=udp%3A%2F%2Ftracker.leechers-paradise.org%3A6969%2Fannounce&tr=udp%3A%2F%2Ftracker.openbittorrent.com%3A6969%2Fannounce&tr=udp%3A%2F%2Ftracker.opentrackr.org%3A1337&tr=udp%3A%2F%2Ftracker.pirateparty.gr%3A6969%2Fannounce&tr=udp%3A%2F%2Ftracker.tiny-vps.com%3A6969%2Fannounce&tr=udp%3A%2F%2Ftracker.torrent.eu.org%3A451%2Fannounce
# https://www.libtorrent.org/python_binding.html

import libtorrent as lt
import threading
import argparse
import sys
import os
from pathlib import Path

def download_torrent(link, save_path):
    ses = lt.session()
    ses.listen_on(6881, 6891)
    params = {
        'save_path': save_path
        
    }
    handle = lt.add_magnet_uri(ses, link, params)
    ses.start_dht()

    print(f"Starting download for: {handle.name()}")
    paused = False

    while handle.status().state != lt.torrent_status.seeding:
        s = handle.status()
        state_str = ['queued', 'checking', 'downloading metadata', 'downloading', 'finished', 'seeding', 'allocating']
        print(f"\rProgress: {s.progress * 100:.2f}%  Down: {s.download_rate / 1000:.1f} kB/s  Up: {s.upload_rate / 1000:.1f} kB/s  Peers: {s.num_peers}  Seeds: {s.num_seeds}  Num Pieces: {s.num_pieces}  List Seeds: {s.list_seeds}   State: {state_str[s.state]}     ", end = "")
        
        threading.Event().wait(1)  # Esperar 1 segundo antes de verificar el estado nuevamente

        # Preguntar si se desea pausar o reanudar la descarga
        """if not paused:
            pause_input = input("¿Desea pausar la descarga? (S/N): ")
            if pause_input.lower() == 's':
                ses.pause()
                paused = True
                print("Descarga pausada")
        else:
            resume_input = input("¿Desea reanudar la descarga? (S/N): ")
            if resume_input.lower() == 's':
                ses.resume()
                paused = False
                print("Descarga reanudada")
        """
    print(f"Download completed for: {handle.name()}")

# Obtener el directorio de descarga por defecto (carpeta de descargas del usuario de Windows)
default_save_path = str(Path.home() / "Downloads")
# Configurar el argumento de línea de comandos
parser = argparse.ArgumentParser(description='Torrent Client')
parser.add_argument('save_path', type=str, nargs='?', default=default_save_path, help='Directorio de descarga (opcional)')

# Obtener el directorio de descarga del argumento de línea de comandos
args = parser.parse_args(sys.argv[1:])
save_path = args.save_path

# Solicitar los enlaces magnet o archivos .torrent al usuario
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