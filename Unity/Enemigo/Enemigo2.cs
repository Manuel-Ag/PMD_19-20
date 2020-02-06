using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Enemigo2 : MonoBehaviour
{
    [SerializeField] private int health = 3;
    [SerializeField] private float speed = 1f;
    [SerializeField] private Transform pointA, pointB;
    [SerializeField] private float attackRange = 2f;
    [SerializeField] private float hitDelay = 0.5f;

    private GameObject player;
    private Vector3 currentTarget;
    private Animator anim;
    private float distance;
    private bool canDamage = true;

    void Start()
    {
        anim = GetComponent<Animator>();
        // Posición de inicio
        currentTarget = pointA.position;
        // Referencia al jugador
        player = GameObject.FindGameObjectWithTag("Player");
    }

    public virtual void Update()
    {
        // Caculamos la distancia al Jugador
        distance = Vector2.Distance(player.transform.localPosition, transform.localPosition);
        Movement();
    }

    public virtual void Movement()
    {
        // Si estamos en el punto A vamos al B y viceversa
        if (transform.position == pointA.position)
        {
            currentTarget = pointB.position;
            // No rotamos el objeto
            transform.localRotation = Quaternion.Euler(0, 0, 0);
        }
        else if (transform.position == pointB.position)
        {
            currentTarget = pointA.position;
            // Rotamos el objeto en el eje y 180 grados
            transform.localRotation = Quaternion.Euler(0, 180, 0);
        }

        // Si está cerca ataca y no se mueve
        if (distance <= attackRange)
        {
            anim.SetBool("attacking", true);
        }
        else
        {
            // Movimiento a currentTarget cuando no ataca
            transform.position = Vector2.MoveTowards(transform.position, currentTarget, speed * Time.deltaTime);
            anim.SetBool("attacking", false);
        }
    }

    private void OnTriggerEnter2D(Collider2D other)
    {
        // Referencia al objeto jugador
        Comprobar_suelo script_jugador = player.GetComponent<Comprobar_suelo>();
        // Llamada al método de jugador
        script_jugador.danyo("araña");
        // Pausa entre daños en los ataques
        canDamage = false;
        damagePause();
    }

    // Coroutine que permite cambiar el valor de la variable cada hitDelay
    IEnumerator damagePause()
    {
        // Este código se ejecutará durante varios frames hasta que pase el tiempo
        yield return new WaitForSeconds(hitDelay);
        // Ha pasado el tiempo definido, se ejecuta la siguiente línea
        canDamage = true;
    }
}
