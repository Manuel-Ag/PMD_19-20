using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class JugadorSuelo : MonoBehaviour
{
    [SerializeField] private float _fuerzaSalto = 100f;
    [SerializeField] private int _velocidadHorizontal = 10;
    [SerializeField] private LayerMask mascaraSuelo;
    [SerializeField] private Transform comprobarSuelo;
    [SerializeField] private float radioComprobarSuelo = 0.07f;


    public bool enSuelo = false;

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
        if (Input.GetKey(KeyCode.Space) && enSuelo)
            _isJump = true;
        else
            _isJump = false;
    }

    private void FixedUpdate()
    {
        _rigid.velocity = new Vector2(Input.GetAxis("Horizontal") * _velocidadHorizontal, _rigid.velocity.y);
        //enSuelo = comprobarSuelo1();
        enSuelo = comprobarSuelo3();


        if (_isJump)
        {
            _rigid.AddForce(new Vector2(0, _fuerzaSalto));
        }
    }

    private bool comprobarSuelo1()
    {
        return Physics2D.OverlapCircle(comprobarSuelo.position, radioComprobarSuelo, mascaraSuelo);
    }

    private bool comprobarSuelo2()
    {
        float tamanyoRayo = 1.9f;
        // Solo pintar para Debug
        Debug.DrawRay(transform.position, new Vector2(0, tamanyoRayo* - 1), Color.cyan);
        // Calcula
        return Physics2D.Raycast(transform.position, Vector2.down, tamanyoRayo, mascaraSuelo);
    }

    private bool comprobarSuelo3()
    {
        // Dibuja
        BoxCast(new Vector2(transform.position.x, transform.position.y - 1.8f), new Vector2(2.5f, 0.5f),
            0f, new Vector2(0, 0), 0f, mascaraSuelo);
        // Calcula
        return Physics2D.BoxCast(new Vector2(transform.position.x, transform.position.y - 1.8f), new Vector2(2.5f, 0.5f),
            0f, new Vector2(0, 0), 0f, mascaraSuelo);
    }


    static public RaycastHit2D BoxCast(Vector2 origen, Vector2 size, float angle, Vector2 direction, float distance, int mask)
    {
        RaycastHit2D hit = Physics2D.BoxCast(origen, size, angle, direction, distance, mask);

        //Setting up the points to draw the cast
        Vector2 p1, p2, p3, p4, p5, p6, p7, p8;
        float w = size.x * 0.5f;
        float h = size.y * 0.5f;
        p1 = new Vector2(-w, h);
        p2 = new Vector2(w, h);
        p3 = new Vector2(w, -h);
        p4 = new Vector2(-w, -h);

        Quaternion q = Quaternion.AngleAxis(angle, new Vector3(0, 0, 1));
        p1 = q * p1;
        p2 = q * p2;
        p3 = q * p3;
        p4 = q * p4;

        p1 += origen;
        p2 += origen;
        p3 += origen;
        p4 += origen;

        Vector2 realDistance = direction.normalized * distance;
        p5 = p1 + realDistance;
        p6 = p2 + realDistance;
        p7 = p3 + realDistance;
        p8 = p4 + realDistance;


        //Drawing the cast
        Color castColor = hit ? Color.red : Color.blue;
        Debug.DrawLine(p1, p2, castColor);
        Debug.DrawLine(p2, p3, castColor);
        Debug.DrawLine(p3, p4, castColor);
        Debug.DrawLine(p4, p1, castColor);

        Debug.DrawLine(p5, p6, castColor);
        Debug.DrawLine(p6, p7, castColor);
        Debug.DrawLine(p7, p8, castColor);
        Debug.DrawLine(p8, p5, castColor);

        Debug.DrawLine(p1, p5, Color.grey);
        Debug.DrawLine(p2, p6, Color.grey);
        Debug.DrawLine(p3, p7, Color.grey);
        Debug.DrawLine(p4, p8, Color.grey);
        if (hit)
        {
            Debug.DrawLine(hit.point, hit.point + hit.normal.normalized * 0.2f, Color.yellow);
        }

        return hit;
    }

}
