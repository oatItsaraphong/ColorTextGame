import socket
import threading


def handle_client(client_socket):
    while True:
        request = client_socket.recv(1024)
        
        if (recv_len < 1024):
            break

        print ("[GET] %s " % request)

    client_socket.close()


soc = socket.socket()         # Create a socket object
host = "localhost" # Get local machine name
port = 2004                # Reserve a port for your service.
soc.bind((host, port))       # Bind to the port
soc.listen(5)                 # Now wait for client connection.
print ("Start Listenting")
while True:
    conn, addr = soc.accept()     # Establish connection with client.
    print ("Got connection from",addr)

    client_handler = threading.Thread(target=client_handler,args=(conn,))
    client_handler.start()
    
    