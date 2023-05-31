package main.Interfaces;

/**
 * Se usa para que el chat mantenga comunicacion con el servidor
 */
public interface IChat {
    /**
     * Le avisa al chat que llego un mensjae
     * @param message mensaje desde el servidor
     */
    void onMessage(String message);
}
