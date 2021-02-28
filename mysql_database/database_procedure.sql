use recruitment;

delimiter \\
create procedure search_candidates(in first_name_in varchar(45),
                                   in last_name_in varchar(45),
                                   in skill_in varchar(45))
begin
	
    set @q = 'select * from candidate as c ';
    if(skill_in != '') then
		
		set @q = concat(@q, ',skill as s
                 where c.id = s.candidate ');
		set @q = concat(@q, ' and true ');
		set @q = concat(@q, ' and s.description like ', '"%', skill_in ,'%"');
	else
     set @q = concat(@q, ' where true ');
    end if;
    
    if(first_name_in != '') then
		set @q = concat(@q, 'and
        c.first_name like ', '"%', first_name_in,'%"');
    end if;
    
    if(last_name_in != '') then
		set @q = concat(@q, 'and
        c.last_name like ' , '"%', last_name_in,'%"');
    end if;
    
    PREPARE stmt FROM @q;
    EXECUTE stmt;
    DEALLOCATE PREPARE stmt;
    
end\\
delimiter ;
