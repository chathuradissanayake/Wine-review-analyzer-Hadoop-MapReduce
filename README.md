
# 🍇 Wine Review Analyzer Using Hadoop MapReduce

This project analyzes wine review data using Java-based Hadoop MapReduce. It computes the average wine price per wine variety from a CSV dataset.

---

## 🚀 Setup Instructions

### 1️⃣ Install WSL and Ubuntu on Windows

Open PowerShell as Administrator and run:

```bash
wsl --install
```

After installation, restart and launch Ubuntu. Set up your username and password.  
*(If Ubuntu is not installed, use Microsoft Store to install it.)*

Update Ubuntu:

```bash
sudo apt update && sudo apt upgrade
```

---

### 2️⃣ Setup SSH (Required for Hadoop)

Remove any existing OpenSSH server:

```bash
sudo apt --assume-yes remove openssh-server
```

Reinstall OpenSSH server:

```bash
sudo apt --assume-yes install openssh-server
```

Generate SSH Key:

```bash
ssh-keygen -t rsa
```

Add key to authorized keys:

```bash
cat ~/.ssh/id_rsa.pub >> ~/.ssh/authorized_keys
chmod 600 ~/.ssh/authorized_keys
```

Start SSH service:

```bash
sudo service ssh start
```

Test SSH:

```bash
ssh localhost
```

---

### 3️⃣ Install Java (Required by Hadoop)

```bash
sudo apt install openjdk-11-jdk
```

---

### 4️⃣ Download and Extract Hadoop

```bash
wget -c https://downloads.apache.org/hadoop/common/hadoop-3.3.6/hadoop-3.3.6.tar.gz
tar -xvzf hadoop-3.3.6.tar.gz
mv hadoop-3.3.6 ~/hadoop-3.3.6
```

---

### 5️⃣ Configure Hadoop

Navigate to Hadoop folder:

```bash
cd ~/hadoop-3.3.6
```

Edit configuration files in `etc/hadoop/`:

- `core-site.xml`
- `hdfs-site.xml`
- `mapred-site.xml`
- `yarn-site.xml`

*Refer to the official Hadoop documentation or your course guide for specific configuration values.*

---

### 6️⃣ Set Environment Variables

Edit `.bashrc`:

```bash
code ~/.bashrc
```

Add at the end:

```bash
export JAVA_HOME=/usr/lib/jvm/java-11-openjdk-amd64
export HADOOP_HOME=~/hadoop-3.3.6
export PATH=$PATH:$HADOOP_HOME/bin:$HADOOP_HOME/sbin
export HADOOP_CONF_DIR=$HADOOP_HOME/etc/hadoop
```

Apply changes:

```bash
source ~/.bashrc
```

---

### 7️⃣ Format HDFS and Start Hadoop

```bash
cd ~/hadoop-3.3.6
hdfs namenode -format
start-dfs.sh
start-yarn.sh
```

---

### 8️⃣ Prepare Dataset

Place your CSV file in a Linux path:

```bash
/home/<username>/wine-review-analyzer/data/winemag-data_first150k_values.csv
```

Create HDFS directory and upload dataset:

```bash
hdfs dfs -mkdir -p /user/<username>
hdfs dfs -put /home/<username>/wine-review-analyzer/data/winemag-data_first150k_values.csv /user/<username>/
```

---

### 9️⃣ Compile the Java Project

Compile Java files and build the JAR:

```bash
cd ~/wine-review-analyzer
mkdir -p classes
javac -classpath "$HADOOP_HOME/share/hadoop/common/*:$HADOOP_HOME/share/hadoop/mapreduce/*" -d classes src/*.java
jar -cvf wine-review-analyzer.jar -C classes/ .
```

---

## 🔧 Run the MapReduce Job

```bash
hadoop jar wine-review-analyzer.jar AveragePricePerVariety /user/<username>/winemag-data_first150k_values.csv /user/<username>/output-wine
```

---

## 📃 View Output

```bash
hdfs dfs -cat /user/<username>/output-wine/part-r-00000
```

---

## 📁 Clean Previous Outputs

Remove existing output directory:

```bash
hdfs dfs -rm -r /user/<username>/output-wine
```

Re-run the job after cleaning.

---

## 📊 Input File Listing

```bash
hdfs dfs -ls /user/<username>/
```

---

## 💳 Output Description

This project processes a dataset of 150,000+ wine reviews and calculates the average price for each wine variety.  
It excludes entries with missing or malformed price or variety fields.

---

## 📰 Hadoop Web Interfaces

- **NameNode (HDFS UI)**: [http://localhost:9870](http://localhost:9870)  
- **ResourceManager (YARN UI)**: [http://localhost:8088](http://localhost:8088)

---

## 🔚 Stop Hadoop

```bash
stop-dfs.sh
stop-yarn.sh
wsl --shutdown
```

---

## 🤝 Contributions

- Chathura Dissanayake
- Malindu Dissanayaka
- Lahiru Maduwantha
