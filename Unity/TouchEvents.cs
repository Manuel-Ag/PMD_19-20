using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class TouchEvents : MonoBehaviour
{
    // Update is called once per frame
    void Update()
    {
        //VisualizarToques();
        //VisualizarAccionToques();
        ///VisualizarTodosAccionToques();
        //VisualizarAccelerometro(); 
        VisualizarToquePantalla();
    }

    // Visualiza todos los toques en pantalla y su posición
    private void VisualizarToques()
    {
        // Cantidad de dedos en pantalla
        Debug.Log(Input.touchCount);

        // Imprimir al posición de cada toque
        foreach (Touch touch in Input.touches)
        {
            Debug.Log(touch.position);
        }
    }

    // Muestra si se ha realizado la acción de toque o dejar de toque
    private void VisualizarAccionToques()
    {
        // Si hay algún toque en pantalla
        if (Input.touchCount > 0)
        {
            // Primer toque
            Touch touch = Input.GetTouch(0);
            if (touch.phase == TouchPhase.Began)
                Debug.Log("Toque en " + touch.position);
            else if (touch.phase == TouchPhase.Ended)
                Debug.Log("Se levantó el dedo en " + touch.position);
        }
    }

    // Combinación de los ejemplos anteriores, muestra el touch de cada dedo
    private void VisualizarTodosAccionToques()
    {
        for (int i = 0; i < Input.touchCount; i++)
        {
            Touch touch = Input.GetTouch(i);
            if (touch.phase == TouchPhase.Began)
                Debug.Log("Toque en " + touch.position + "dedo " + i);
            else if (touch.phase == TouchPhase.Ended)
                Debug.Log("Se levantó el dedo en " + touch.position + "dedo " + i);
        }
    }

    // Accelerómetro
    private void VisualizarAccelerometro()
    {
        Debug.Log("Acelerómetro: " + Input.acceleration);
    }

    // Visualiza donde ha realizado la acción toque respecto a la pantalla
    private void VisualizarToquePantalla()
    {
        // Click izquierdo del ratón, equivale a touch
        if (Input.GetMouseButton(0))
        {
            // Posición respecto a la cámara
            Debug.Log("ScreenToWorldPoint" + Camera.main.ScreenToWorldPoint(Input.mousePosition));
            // Valores entre 0 y 1 de los tres ejes
            Debug.Log("ScreenToViewPoint" + Camera.main.ScreenToViewportPoint(Input.mousePosition));
        }
    }
}
