using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Enemigo1 : MonoBehaviour
{
    [SerializeField] private int vida = 3;
    [SerializeField] private int velocidad = 3;
    [SerializeField] private int fuerzaEmpujar = 200;
    [SerializeField] private Transform pointA, pointB;

    private Vector3 currentTarget;
    private SpriteRenderer sprite;

    // Start is called before the first frame update
    void Start()
    {
        currentTarget = pointA.position;
        sprite = GetComponent<SpriteRenderer>();
    }

    // Update is called once per frame
    void Update()
    {
        Movement();
    }

    public void Movement()
    {
        if (transform.position == pointA.position)
        {
            currentTarget = pointB.position;
            sprite.flipX = false;
        }
        else if (transform.position == pointB.position)
        {
            currentTarget = pointA.position;
            sprite.flipX = true;
        }
        transform.position = Vector2.MoveTowards(transform.position, currentTarget, velocidad * Time.deltaTime);
    }

    private void OnTriggerEnter2D(Collider2D other)
    {
        if (other.attachedRigidbody.velocity.y < 0)
            this.vida -= 1;
        if (vida <= 0)
            Destroy(this.gameObject);
        other.attachedRigidbody.velocity = new Vector2(0, fuerzaEmpujar);
    }
}
