$(document).ready(function(){
		let board;
		let arrayOfBombs = [];
		let remainingFlags;
		let tilesWithoutBombs;
		let vForTimer;
		let timeSeconds;
		let highScore = 0;
		let gameIsActive;
		let gameHasStarted;
		let height;
		let width;
	$("button.boardCreator").click(function(){
		height = document.getElementById("height").value;
		width = document.getElementById("width").value;
		let numBombs = document.getElementById("numBombs").value;
		let size = height * width;
		
		
		gameIsActive = true;
		gameHasStarted = true;
		timeSeconds = 0;
		$("div.timer").empty();
		$("div.timer").append(timeSeconds);
		timeStop(vForTimer);
		
		$("div.bombsleft").empty();
		$("div.bombsleft").append(numBombs);
		remainingFlags = numBombs;
		tilesWithoutBombs = size-numBombs;
		
		
		board = new Array();
		for (let i=0; i < height; i++) {
			board[i] = new Array();
		}
//		alert("Height: "+height+" Width: "+width+" NumBombs: "+numBombs+ "Flags Left: " + remainingFlags);
		if (height<8 || height>30) {
			alert("Height cannot be less than 8 or greater than 30");
			return;
		} else if (width<8 || width>40) {
			alert("Width cannot be less than 8 or greater than 40");
			return;
		} else if (numBombs<1 || numBombs>size-1) {
			alert("Number of bombs must be in between 1 and the size of the board");
			return;
		}
		generateTheBoard(height, width, numBombs, size, arrayOfBombs, board);		
//		alert("board created");
		//get number of bombs adjecent to each space
	for (let h = 0; h < height; h++) {
		for (let w = 0; w < width; w++) {
			let tile = board[h][w];
			//below is counting the number of adjacent tiles that have a bomb
			if ($(tile).data("containsmine") == false) {					
					let adjBombs = 0;
					
					let adjTiles = [];
				 
					if(h==0 && w==0) { //top left
						let down = board[h+1][w];
						let diagRight = board[h+1][w+1];
						let right = board[h][w+1];
						adjTiles.push(right);
						adjTiles.push(diagRight);
						adjTiles.push(down);
					} else if(h==0 && w==width-1) { //top right
						let left = board[h][w-1];
						let diagLeft = board[h+1][w-1];
						let down = board[h+1][w];
						adjTiles.push(left);
						adjTiles.push(diagLeft);
						adjTiles.push(down);
					} else if(h==height-1 && w==0) { //bottom left
						let right = board[h][w+1];
						let diagRight = board[h-1][w+1];
						let up = board[h-1][w];
						adjTiles.push(right);
						adjTiles.push(diagRight);
						adjTiles.push(up);
					} else if(h==height-1 && w==width-1) { //bottom right
						let left = board[h][w-1];
						let diagLeft = board[h-1][w-1];
						let up = board[h-1][w];
						adjTiles.push(left);
						adjTiles.push(diagLeft);
						adjTiles.push(up);
					} else if(h==0) { //top row
						let left = board[h][w-1];
						let downLeft = board[h+1][w-1];
						let down = board[h+1][w];
						let downRight = board[h+1][w+1];
						let right = board[h][w+1];
						adjTiles.push(left);
						adjTiles.push(downLeft);
						adjTiles.push(down);
						adjTiles.push(downRight);
						adjTiles.push(right);
					} else if(h==height-1) { //bottom row
						let left = board[h][w-1];
						let upLeft = board[h-1][w-1];
						let up = board[h-1][w];
						let upRight = board[h-1][w+1];
						let right = board[h][w+1];
						adjTiles.push(left);
						adjTiles.push(upLeft);
						adjTiles.push(up);
						adjTiles.push(upRight);
						adjTiles.push(right);
					} else if(w==0) { //left column
						let up = board[h-1][w];
						let upRight = board[h-1][w+1];
						let right = board[h][w+1];
						let downRight = board[h+1][w+1];
						let down = board[h+1][w];
						adjTiles.push(up);
						adjTiles.push(upRight);
						adjTiles.push(right);
						adjTiles.push(downRight);
						adjTiles.push(down);
					} else if(w==width-1) { //right column
						let up = board[h-1][w];
						let upLeft = board[h-1][w-1];
						let left = board[h][w-1];
						let downLeft = board[h+1][w-1];
						let down = board[h+1][w];
						adjTiles.push(up);
						adjTiles.push(upLeft);
						adjTiles.push(left);
						adjTiles.push(downLeft);
						adjTiles.push(down);
					} else { //middle tile
						let up = board[h-1][w];
						let upLeft = board[h-1][w-1];
						let left = board[h][w-1];
						let downLeft = board[h+1][w-1];
						let down = board[h+1][w];
						let downRight = board[h+1][w+1];
						let right = board[h][w+1];
						let upRight = board[h-1][w+1];
						adjTiles.push(up);
						adjTiles.push(upLeft);
						adjTiles.push(left);
						adjTiles.push(downLeft);
						adjTiles.push(down);
						adjTiles.push(downRight);
						adjTiles.push(right);
						adjTiles.push(upRight);
					}
				//adds adjTiles array to the tile
				$(tile).data("adjtiles", adjTiles);
				//this checks to see if those adjacent tiles have bombs in them and adds accordingly to the variable of the selected tile.
				for (let x = 0; x < adjTiles.length; x++) {
					if(adjTiles[x].data("containsmine")) {
						adjBombs++;
						}
					}
				//adds the adjBombs variable to the data of the tile
				$(tile).data("adjbombs", adjBombs);
				}
			}
	}	
		
	});
	
	$("div.theBoard").on("click", "#space", function(e) { //when click on a tile
		if(gameHasStarted==true){
			gameHasStarted=false;
			vForTimer = setInterval(timer, 1000);
		}
		if(gameIsActive==true) {
		let adjBombs = $(this).data("adjbombs");
		let isFlagged = $(this).data("flagged");
		if(e.shiftKey) { //if trying to flag
			if(isFlagged) {
				$(this).data("flagged", false);
				$(this).text("?");
				remainingFlags=remainingFlags+1;
				$("div.bombsleft").empty();
				$("div.bombsleft").append(remainingFlags);
			} else {
				if(remainingFlags==0) {
					alert("No flags left!");
					return;
				}
				if($(this).data("opened")==true) {
					return;
				}
				$(this).data("flagged", true);
				$(this).text("!!");
				if(remainingFlags != 0) {
				remainingFlags=remainingFlags-1;
				}
				$("div.bombsleft").empty();
				$("div.bombsleft").append(remainingFlags);
			}
		
		}else{
			if (isFlagged) { //if flagged already
				alert("You should unflag this if you want to open this tile");
			} else if($(this).data("containsmine") == true) { //if click on bomb
				alert("You clicked on a bomb. Game Over.");
				endGame(0, 0, vForTimer);
				$("div.theBoard").empty();
			} else { //if click on regular space
				if($(this).data("opened") == true && $(this).data("adjbombs") != 0) {
					let adjTilesTemp = $(this).data("adjtiles");
					let tempFlags=0;
					for(let r=0; r<adjTilesTemp.length; r++) {
						if(adjTilesTemp[r].data("flagged")==true) {
							tempFlags++;
						}
					} 
					let tempText = $(this).text();
					if(tempText==tempFlags) {
						for(let s=0; s<adjTilesTemp.length; s++) {
							if(adjTilesTemp[s].data("opened")==false && adjTilesTemp[s].data("flagged")==false){
								adjTilesTemp[s].data("opened", true);
								if(adjTilesTemp[s].data("containsmine")==true) {
									alert("You opened a bomb. Game Over.");
									endGame(0, 0, vForTimer);
								} else if(adjTilesTemp[s].data("adjbombs") !=0) {
									adjTilesTemp[s].text(adjTilesTemp[s].data("adjbombs"));
								} if(adjTilesTemp[s].data("adjbombs")==0) {
									adjTilesTemp[s].text("_");
								}
							} 
						}
					}
				} if($(this).data("opened")==true) {
					return;
				}
				
				if(adjBombs != 0) { //if has an adjacent bomb
					$(this).text(adjBombs);
					$(this).data("opened", true);
				} else { //if no adjacent bombs
					$(this).text("_");
					$(this).data("opened", true);
					uncoverNoAdjBombTiles($(this));
				}
			}
		}	
			let tempCount = 0;
			for(let a=0; a<height; a++) {
				for(let b=0; b<width; b++) {
				if(board[a][b].data("opened") == true){ 
				tempCount++;
			}
		}
	}
			if (remainingFlags==0 && tempCount == tilesWithoutBombs) {
				gameIsActive = false;
				let newHighScore = endGame(1, gameIsActive, vForTimer, highScore, timeSeconds);
				highScore = newHighScore;
			}
		}
	}); 
	
	function timer() {
		timeSeconds = timeSeconds+1;
		$("div.timer").empty()
		$("div.timer").append(timeSeconds);
	}
});



	function generateTheBoard(h, w, nB, size, arrayOfBombs, board) {
		$("div.theBoard").empty();
		arrayOfBombs = [];
		
		for(let i=0; i<nB; i++) {
			addBomb(size, arrayOfBombs);
		}
//		alert("arrayOfBombs: "+arrayOfBombs);
		let id = 0;
	
		for(let r=0; r<h; r++) {
			for(let c=0; c<w; c++) {
				if (arrayOfBombs.includes(id)) {
				$("div.theBoard").append("<button type = 'button' class = 'square' id = 'space'>?</button>");
				let buttonToAdd = $("div.theBoard button:last-child");
					$(buttonToAdd).data("idnum", id);
					$(buttonToAdd).data("containsmine", true);
					$(buttonToAdd).data("flagged", false);
					$(buttonToAdd).data("opened", false);
					$(buttonToAdd).data("row", r);
					$(buttonToAdd).data("column", c);
					//$(buttonToAdd).css("color", "red");
					board[r][c] = $(buttonToAdd);
					id++;
			}else{
				$("div.theBoard").append("<button type = 'button' class = 'square' id = 'space'>?</button>");	
				let buttonToAdd = $("div.theBoard button:last-child");
					$(buttonToAdd).data("idnum", id);
					$(buttonToAdd).data("containsmine", false);
					$(buttonToAdd).data("flagged", false);
					$(buttonToAdd).data("opened", false);
					$(buttonToAdd).data("row", r);
					$(buttonToAdd).data("column", c);
					board[r][c] = $(buttonToAdd);	
					id++;
				
			}
		}			
			$("div.theBoard").append("<br>");
	}
}
	
	function addBomb(size, arrayOfBombs) { //random algorithm to put a bomb somewhere in the board
		let random = Math.floor((Math.random()*size)+0);
		if (arrayOfBombs.includes(random)) {
			addBomb(size, arrayOfBombs);
		} else {
			arrayOfBombs.push(random);
			return;
		}
	}

	function uncoverNoAdjBombTiles(clickedTile) {
		let tilesAdj = clickedTile.data("adjtiles");
		let counter = 0;
		for(let i=0; i<tilesAdj.length; i++) {
			if(tilesAdj[i].data("adjbombs")==0){
				checkAdjToAdj(tilesAdj[i]);
			   } else {
				  let currTileAdjBombs = tilesAdj[i].data("adjbombs");
				   tilesAdj[i].data("opened", true);
				   tilesAdj[i].text(currTileAdjBombs);
				 
			   }
		}
	}
	function checkAdjToAdj(aT) {
		if(aT.data("opened")==true || aT.data("flagged")==true) {
			return;
		} else {
			aT.data("opened", true);
			aT.text("_");
			uncoverNoAdjBombTiles(aT);
		}
		
	}
	
	function endGame(result, active, vForTimer, highScore, timeSeconds) {
		if(result==1) {
			alert("You won!");
			timeStop(vForTimer);
			if(highScore==0) {
				$("div.highscore").empty();
				highScore = timeSeconds;
		$("div.highscore").append(highScore);
			} else if(timeSeconds<highScore) {
				highScore = timeSeconds;
				$("div.highscore").empty();
		$("div.highscore").append(highScore);
			}
		} else {
			timeStop(vForTimer);
		}
		return highScore;
	}

	function timeStop(vForTimer) {
		clearInterval(vForTimer);
	}
		


