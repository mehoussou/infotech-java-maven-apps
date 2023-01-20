variable "vpc_cidr_block" {
    default = "10.0.0.0/16"
}
variable "subnet_cidr_block" {
     default = "10.0.10.0/24"
}
variable "avail_zone" {
     default = "us-east-2"
}
variable "env_prefix" {
     default = "infotech"
}
variable "my_ip" {
     default = "75.71.114.225"
}

variable "jenkins_ip" {
     default = ""
}


variable "instance_type" {
     default = "t2.micro"
}