Ene
===

About
-----
This project is a simple music library as a homework in the context of our computer science studies.
For more details about the source code please check out the documentation: https://vivi90.github.io/ene
(Also available as PDF download: https://vivi90.github.io/ene/ene.pdf)

_![Screenshot library tab](docs/images/screenshot_library.png)_

_![Screenshot playlist tab](docs/images/screenshot_playlist.png)_

Naming
------
We have named it after the character 'Ene' from the anime 'Mekakucity Actors'.

Usage
-----
Just compile it or use the attached binary file `bin/ene.jar`.
Currently it supports only WAV (PCM) files.
The library database is saved in a local SQLite file, called `library.db`.
The playlist file is called `playlist.m3u`.

Features
--------
* Create a simple music library
* Play tracks
* Sort tracks by artist, title or genre (ascending or descending)
* Filter tracks by artist, title or genre
* Create a playlist
* Currently available translations: English & German

Requirements
------------
* Java Runtime Environment (tested with OpenJDK 8)
* Doxygen (only for contribution)

Contribution
------------
Please issue **right after** cloning the following command to use all necessary hooks:
`git config --local core.hooksPath .githooks`

Translation
-----------
Please take a look at the directory `src/ene/resources/locales`.

Troubleshooting
---------------
If you run this application on a Linux operating system, the sound doesn't work,
and you get an `LineUnavailableException` in debug mode, then please force Java to use the Pulse Audio system by issuing: `padsp java -jar bin/ene.jar`
You need to start this application with the `-d` argument to enable debug infos.

License
-------
This project is free software under the terms of the GNU General Public License v3 as published by the Free Software Foundation.
It is distributed WITHOUT ANY WARRANTY (without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE).
For more details please see the LICENSE file or: http://www.gnu.org/licenses

Credits
-------
* 2019 by Vivien Richter <vivien-richter@outlook.de> and Mathias Telling <mathias.telling@stud.htwk-leipzig.de>
* Our Git repository: https://github.com/vivi90/ene.git
* Application icon repository (by Google Limited): https://github.com/google/material-design-icons.git
* SQLite JDBC driver library repository: https://bitbucket.org/xerial/sqlite-jdbc
