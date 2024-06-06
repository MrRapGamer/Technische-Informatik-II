#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <fcntl.h>
#include <errno.h>

#define LOCKFILE "/tmp/.fileSema"

void lock_resource()
{
    int fd;

    // Try to create the lock file
    fd = open(LOCKFILE, O_WRONLY | O_CREAT | O_EXCL, 0666); // 0666 is the permission of the file with 6 = read and write permission (110 in binaryr)
    if (fd == -1)
    {
        if (errno == EEXIST)
        {
            printf("Resource is already locked.\n");
            exit(EXIT_FAILURE);
        }
        else
        {
            perror("Error creating lock file");
            exit(EXIT_FAILURE);
        }
    }

    printf("Resource locked.\n");

    // Close the file descriptor as it's no longer needed
    close(fd);
}

void unlock_resource()
{ // Remove the lock file
    if (unlink(LOCKFILE) == -1)
    { // unlink() is used to remove the file unlink returns 0 on success and -1 on failure
        perror("Error removing lock file");
        exit(EXIT_FAILURE);
    }

    printf("Resource unlocked.\n");
}

int main()
{
    lock_resource();
    // Simulate resource usage
    printf("Using the resource...\n");
    sleep(5); // Stuff to do

    unlock_resource();

    return 0;
}
