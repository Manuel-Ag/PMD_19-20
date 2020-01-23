using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Jugador : MonoBehaviour
{
    [SerializeField] private float horizotal;
    [SerializeField] private float vertical;
    [SerializeField] private int velocidad = 2;
    private Rigidbody2D _rigid;

    // Start is called before the first frame update
    void Start()
    {
        _rigid = GetComponent<Rigidbody2D>();
    }

    // Update is called once per frame
    void Update()
    {
        horizotal = Input.GetAxis("Horizontal");
        vertical = Input.GetAxis("Vertical");

        Debug.Log(horizotal);
        Debug.Log(vertical);
    }

    private void FixedUpdate()
    {
        _rigid.velocity = new Vector2(horizotal * velocidad, vertical * velocidad);
    }
}
