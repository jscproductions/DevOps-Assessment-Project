#!/bin/bash

PG_USER="postgres"
DB_NAME="employee_app"
BACKUP_DIR="/mnt/my_ebs_volume/postgresql_backups"

TIMESTAMP=$(date +\%Y\%m\%d\%H\%M\%S)


PGPASSFILE=~/.pgpass pg_dump -h localhost -p 5432 -U $PG_USER -d $DB_NAME -f $BACKUP_DIR/backup_$TIMESTAMP.sql
