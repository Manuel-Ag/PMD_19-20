using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Coroutines : MonoBehaviour
{
    // Update is called once per frame
    void Update()
    {
        if (Input.anyKeyDown)
            //StartCoroutine(Coroutine());
            //StartCoroutine(Coroutine2(1f));
            StartCoroutine(Coroutine3());
    }

    IEnumerator Coroutine()
    {
        Debug.Log("Iniciada corrutina");
        yield return new WaitForSeconds(2f);
        Debug.Log("Finalizada corrutina");
    }

    IEnumerator Coroutine2(float time)
    {
        int i = 0;
        while (i < 10)
        {
            Debug.Log("Bucle corrutina" + i);
            i++;
            yield return new WaitForSeconds(time);
        }
    }

    IEnumerator Coroutine3()
    {
        Debug.Log("Co3: Esperando a que finalice la corrutina 4...");
        yield return StartCoroutine(Coroutine4());
        Debug.Log("Co3: Acabado");
    }

    IEnumerator Coroutine4()
    {
        Debug.Log("Co4: Hola, soy la corrutina4, la 3 está esperando a que yo finalice...");
        yield return new WaitForSeconds(3f);
        Debug.Log("Co4: Acabado");
    }
}
