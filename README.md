Ene
===

Want to add new button

About
-----
This project is a simple extensible music library as a homework in the context of my computer science studies.
For more details about the source code please check out the documentation: https://vivi90.github.io/ene

Naming
------
We have named it after the character 'Ene' from the anime 'Mekakucity Actors'.

Usage
-----
Just create a `Music` folder at the project root directory and compile it.
Currently it supports only WAV files.

Requirements
------------
* Doxygen (only for contribution)

Contribution
------------
Please issue **right after** cloning the following command to use all necessary hooks:
`git config --local core.hooksPath .githooks`

Translation
-----------
Please take a look at the directory `src/resources/locales`.

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
* Git repository: https://github.com/vivi90/ene.git
* Application icon by Google Limited: https://github.com/google/material-design-icons.git
