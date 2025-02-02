package com.tracelink.prodsec.blueprint.app.statement;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * DTO for a base statement. Used to transfer data to the UI and to perform validation during
 * startup when base statements are imported. Contains a method to convert this object to an entity.
 *
 * @author mcool
 */
public class BaseStatementDto {

	@NotBlank(message = "Name cannot be blank")
	private String name;
	@NotBlank(message = "Description cannot be blank")
	private String description;
	@NotNull(message = "Negation cannot be null")
	private boolean negationAllowed;
	@NotEmpty(message = "A base statement must be valid for at least one policy type")
	private Set<@NotBlank(message = "Policy types cannot be blank") String> policyTypes;
	@NotBlank(message = "Evaluated function cannot be blank")
	private String function;
	private List<BaseStatementArgumentDto> arguments = new ArrayList<>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isNegationAllowed() {
		return negationAllowed;
	}

	public void setNegationAllowed(boolean isNegationAllowed) {
		this.negationAllowed = isNegationAllowed;
	}

	public Set<String> getPolicyTypes() {
		return policyTypes;
	}

	public void setPolicyTypes(Set<String> policyTypes) {
		this.policyTypes = policyTypes;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public List<BaseStatementArgumentDto> getArguments() {
		return arguments;
	}

	public void setArguments(List<BaseStatementArgumentDto> arguments) {
		this.arguments = arguments;
	}

	/**
	 * Converts this DTO object to an entity object to be stored in the database.
	 *
	 * @return the entity representation of this DTO
	 */
	public BaseStatementEntity toEntity() {
		BaseStatementEntity baseStatement = new BaseStatementEntity();
		baseStatement.setName(name);
		baseStatement.setDescription(description);
		baseStatement.setNegationAllowed(negationAllowed);
		baseStatement.setArguments(arguments.stream().map(BaseStatementArgumentDto::toEntity)
				.collect(Collectors.toList()));
		return baseStatement;
	}
}
