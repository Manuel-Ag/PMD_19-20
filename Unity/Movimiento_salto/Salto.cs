using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Jugador2 : MonoBehaviour
{
    [SerializeField] float _fuerzaSalto = 5f;
    private Rigidbody2D _rigid;
    private bool _isJump = false;


    // Start is called before the first frame update
    void Start()
    {
        _rigid = GetComponent<Rigidbody2D>();
        
    }

    // Update is called once per frame
    void Update()
    {
        if (Input.GetKey(KeyCode.Space))
            _isJump = true;
        else
            _isJump = false;
    }

    private void FixedUpdate()
    {
        if (_isJump)
        {
            _rigid.AddForce(new Vector2(0, _fuerzaSalto));
        }
    }
}
