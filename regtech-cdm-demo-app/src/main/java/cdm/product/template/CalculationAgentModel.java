package cdm.product.template;

import cdm.base.datetime.BusinessCenterEnum;
import cdm.observable.asset.CalculationAgent;
import cdm.product.template.CalculationAgentModel;
import cdm.product.template.CalculationAgentModel.CalculationAgentModelBuilder;
import cdm.product.template.CalculationAgentModel.CalculationAgentModelBuilderImpl;
import cdm.product.template.CalculationAgentModel.CalculationAgentModelImpl;
import cdm.product.template.meta.CalculationAgentModelMeta;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaAttribute;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 * This class corresponds to the FpML CalculationAgent.model.
 * @version ${project.version}
 */
@RosettaDataType(value="CalculationAgentModel", builder=CalculationAgentModel.CalculationAgentModelBuilderImpl.class, version="${project.version}")
public interface CalculationAgentModel extends RosettaModelObject {

	CalculationAgentModelMeta metaData = new CalculationAgentModelMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * The ISDA calculation agent responsible for performing duties as defined in the applicable product definitions.
	 */
	CalculationAgent getCalculationAgent();
	/**
	 * The city in which the office through which ISDA Calculation Agent is acting for purposes of the transaction is located. The short-form confirm for a trade that is executed under a Sovereign or Asia-Pacific Master Confirmation Agreement (MCA), does not need to specify the Calculation Agent. However, the confirm does need to specify the Calculation Agent city. This is due to the fact that the MCA sets the value for Calculation Agent but does not set the value for Calculation Agent city.
	 */
	BusinessCenterEnum getCalculationAgentBusinessCenter();

	/*********************** Build Methods  ***********************/
	CalculationAgentModel build();
	
	CalculationAgentModel.CalculationAgentModelBuilder toBuilder();
	
	static CalculationAgentModel.CalculationAgentModelBuilder builder() {
		return new CalculationAgentModel.CalculationAgentModelBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends CalculationAgentModel> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends CalculationAgentModel> getType() {
		return CalculationAgentModel.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("calculationAgent"), processor, CalculationAgent.class, getCalculationAgent());
		processor.processBasic(path.newSubPath("calculationAgentBusinessCenter"), BusinessCenterEnum.class, getCalculationAgentBusinessCenter(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface CalculationAgentModelBuilder extends CalculationAgentModel, RosettaModelObjectBuilder {
		CalculationAgent.CalculationAgentBuilder getOrCreateCalculationAgent();
		CalculationAgent.CalculationAgentBuilder getCalculationAgent();
		CalculationAgentModel.CalculationAgentModelBuilder setCalculationAgent(CalculationAgent calculationAgent);
		CalculationAgentModel.CalculationAgentModelBuilder setCalculationAgentBusinessCenter(BusinessCenterEnum calculationAgentBusinessCenter);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("calculationAgent"), processor, CalculationAgent.CalculationAgentBuilder.class, getCalculationAgent());
			processor.processBasic(path.newSubPath("calculationAgentBusinessCenter"), BusinessCenterEnum.class, getCalculationAgentBusinessCenter(), this);
		}
		

		CalculationAgentModel.CalculationAgentModelBuilder prune();
	}

	/*********************** Immutable Implementation of CalculationAgentModel  ***********************/
	class CalculationAgentModelImpl implements CalculationAgentModel {
		private final CalculationAgent calculationAgent;
		private final BusinessCenterEnum calculationAgentBusinessCenter;
		
		protected CalculationAgentModelImpl(CalculationAgentModel.CalculationAgentModelBuilder builder) {
			this.calculationAgent = ofNullable(builder.getCalculationAgent()).map(f->f.build()).orElse(null);
			this.calculationAgentBusinessCenter = builder.getCalculationAgentBusinessCenter();
		}
		
		@Override
		@RosettaAttribute("calculationAgent")
		public CalculationAgent getCalculationAgent() {
			return calculationAgent;
		}
		
		@Override
		@RosettaAttribute("calculationAgentBusinessCenter")
		public BusinessCenterEnum getCalculationAgentBusinessCenter() {
			return calculationAgentBusinessCenter;
		}
		
		@Override
		public CalculationAgentModel build() {
			return this;
		}
		
		@Override
		public CalculationAgentModel.CalculationAgentModelBuilder toBuilder() {
			CalculationAgentModel.CalculationAgentModelBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(CalculationAgentModel.CalculationAgentModelBuilder builder) {
			ofNullable(getCalculationAgent()).ifPresent(builder::setCalculationAgent);
			ofNullable(getCalculationAgentBusinessCenter()).ifPresent(builder::setCalculationAgentBusinessCenter);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			CalculationAgentModel _that = getType().cast(o);
		
			if (!Objects.equals(calculationAgent, _that.getCalculationAgent())) return false;
			if (!Objects.equals(calculationAgentBusinessCenter, _that.getCalculationAgentBusinessCenter())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (calculationAgent != null ? calculationAgent.hashCode() : 0);
			_result = 31 * _result + (calculationAgentBusinessCenter != null ? calculationAgentBusinessCenter.getClass().getName().hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "CalculationAgentModel {" +
				"calculationAgent=" + this.calculationAgent + ", " +
				"calculationAgentBusinessCenter=" + this.calculationAgentBusinessCenter +
			'}';
		}
	}

	/*********************** Builder Implementation of CalculationAgentModel  ***********************/
	class CalculationAgentModelBuilderImpl implements CalculationAgentModel.CalculationAgentModelBuilder {
	
		protected CalculationAgent.CalculationAgentBuilder calculationAgent;
		protected BusinessCenterEnum calculationAgentBusinessCenter;
	
		public CalculationAgentModelBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("calculationAgent")
		public CalculationAgent.CalculationAgentBuilder getCalculationAgent() {
			return calculationAgent;
		}
		
		@Override
		public CalculationAgent.CalculationAgentBuilder getOrCreateCalculationAgent() {
			CalculationAgent.CalculationAgentBuilder result;
			if (calculationAgent!=null) {
				result = calculationAgent;
			}
			else {
				result = calculationAgent = CalculationAgent.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("calculationAgentBusinessCenter")
		public BusinessCenterEnum getCalculationAgentBusinessCenter() {
			return calculationAgentBusinessCenter;
		}
		
	
		@Override
		@RosettaAttribute("calculationAgent")
		public CalculationAgentModel.CalculationAgentModelBuilder setCalculationAgent(CalculationAgent calculationAgent) {
			this.calculationAgent = calculationAgent==null?null:calculationAgent.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("calculationAgentBusinessCenter")
		public CalculationAgentModel.CalculationAgentModelBuilder setCalculationAgentBusinessCenter(BusinessCenterEnum calculationAgentBusinessCenter) {
			this.calculationAgentBusinessCenter = calculationAgentBusinessCenter==null?null:calculationAgentBusinessCenter;
			return this;
		}
		
		@Override
		public CalculationAgentModel build() {
			return new CalculationAgentModel.CalculationAgentModelImpl(this);
		}
		
		@Override
		public CalculationAgentModel.CalculationAgentModelBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public CalculationAgentModel.CalculationAgentModelBuilder prune() {
			if (calculationAgent!=null && !calculationAgent.prune().hasData()) calculationAgent = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getCalculationAgent()!=null && getCalculationAgent().hasData()) return true;
			if (getCalculationAgentBusinessCenter()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public CalculationAgentModel.CalculationAgentModelBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			CalculationAgentModel.CalculationAgentModelBuilder o = (CalculationAgentModel.CalculationAgentModelBuilder) other;
			
			merger.mergeRosetta(getCalculationAgent(), o.getCalculationAgent(), this::setCalculationAgent);
			
			merger.mergeBasic(getCalculationAgentBusinessCenter(), o.getCalculationAgentBusinessCenter(), this::setCalculationAgentBusinessCenter);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			CalculationAgentModel _that = getType().cast(o);
		
			if (!Objects.equals(calculationAgent, _that.getCalculationAgent())) return false;
			if (!Objects.equals(calculationAgentBusinessCenter, _that.getCalculationAgentBusinessCenter())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (calculationAgent != null ? calculationAgent.hashCode() : 0);
			_result = 31 * _result + (calculationAgentBusinessCenter != null ? calculationAgentBusinessCenter.getClass().getName().hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "CalculationAgentModelBuilder {" +
				"calculationAgent=" + this.calculationAgent + ", " +
				"calculationAgentBusinessCenter=" + this.calculationAgentBusinessCenter +
			'}';
		}
	}
}
