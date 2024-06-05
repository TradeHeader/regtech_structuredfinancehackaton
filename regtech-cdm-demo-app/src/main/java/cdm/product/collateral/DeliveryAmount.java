package cdm.product.collateral;

import cdm.product.collateral.DeliveryAmount;
import cdm.product.collateral.DeliveryAmount.DeliveryAmountBuilder;
import cdm.product.collateral.DeliveryAmount.DeliveryAmountBuilderImpl;
import cdm.product.collateral.DeliveryAmount.DeliveryAmountImpl;
import cdm.product.collateral.DeliveryAmountElectionEnum;
import cdm.product.collateral.meta.DeliveryAmountMeta;
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
 * A class to specify the application of Interest Amount with respect the Delivery Amount.
 * @version ${project.version}
 */
@RosettaDataType(value="DeliveryAmount", builder=DeliveryAmount.DeliveryAmountBuilderImpl.class, version="${project.version}")
public interface DeliveryAmount extends RosettaModelObject {

	DeliveryAmountMeta metaData = new DeliveryAmountMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * The standard election as specified by an enumeration.
	 */
	DeliveryAmountElectionEnum getStandardElection();
	/**
	 * The custom election that might be specified by the parties to the agreement.
	 */
	String getCustomElection();

	/*********************** Build Methods  ***********************/
	DeliveryAmount build();
	
	DeliveryAmount.DeliveryAmountBuilder toBuilder();
	
	static DeliveryAmount.DeliveryAmountBuilder builder() {
		return new DeliveryAmount.DeliveryAmountBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends DeliveryAmount> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends DeliveryAmount> getType() {
		return DeliveryAmount.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("standardElection"), DeliveryAmountElectionEnum.class, getStandardElection(), this);
		processor.processBasic(path.newSubPath("customElection"), String.class, getCustomElection(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface DeliveryAmountBuilder extends DeliveryAmount, RosettaModelObjectBuilder {
		DeliveryAmount.DeliveryAmountBuilder setStandardElection(DeliveryAmountElectionEnum standardElection);
		DeliveryAmount.DeliveryAmountBuilder setCustomElection(String customElection);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("standardElection"), DeliveryAmountElectionEnum.class, getStandardElection(), this);
			processor.processBasic(path.newSubPath("customElection"), String.class, getCustomElection(), this);
		}
		

		DeliveryAmount.DeliveryAmountBuilder prune();
	}

	/*********************** Immutable Implementation of DeliveryAmount  ***********************/
	class DeliveryAmountImpl implements DeliveryAmount {
		private final DeliveryAmountElectionEnum standardElection;
		private final String customElection;
		
		protected DeliveryAmountImpl(DeliveryAmount.DeliveryAmountBuilder builder) {
			this.standardElection = builder.getStandardElection();
			this.customElection = builder.getCustomElection();
		}
		
		@Override
		@RosettaAttribute("standardElection")
		public DeliveryAmountElectionEnum getStandardElection() {
			return standardElection;
		}
		
		@Override
		@RosettaAttribute("customElection")
		public String getCustomElection() {
			return customElection;
		}
		
		@Override
		public DeliveryAmount build() {
			return this;
		}
		
		@Override
		public DeliveryAmount.DeliveryAmountBuilder toBuilder() {
			DeliveryAmount.DeliveryAmountBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(DeliveryAmount.DeliveryAmountBuilder builder) {
			ofNullable(getStandardElection()).ifPresent(builder::setStandardElection);
			ofNullable(getCustomElection()).ifPresent(builder::setCustomElection);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			DeliveryAmount _that = getType().cast(o);
		
			if (!Objects.equals(standardElection, _that.getStandardElection())) return false;
			if (!Objects.equals(customElection, _that.getCustomElection())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (standardElection != null ? standardElection.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (customElection != null ? customElection.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "DeliveryAmount {" +
				"standardElection=" + this.standardElection + ", " +
				"customElection=" + this.customElection +
			'}';
		}
	}

	/*********************** Builder Implementation of DeliveryAmount  ***********************/
	class DeliveryAmountBuilderImpl implements DeliveryAmount.DeliveryAmountBuilder {
	
		protected DeliveryAmountElectionEnum standardElection;
		protected String customElection;
	
		public DeliveryAmountBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("standardElection")
		public DeliveryAmountElectionEnum getStandardElection() {
			return standardElection;
		}
		
		@Override
		@RosettaAttribute("customElection")
		public String getCustomElection() {
			return customElection;
		}
		
	
		@Override
		@RosettaAttribute("standardElection")
		public DeliveryAmount.DeliveryAmountBuilder setStandardElection(DeliveryAmountElectionEnum standardElection) {
			this.standardElection = standardElection==null?null:standardElection;
			return this;
		}
		@Override
		@RosettaAttribute("customElection")
		public DeliveryAmount.DeliveryAmountBuilder setCustomElection(String customElection) {
			this.customElection = customElection==null?null:customElection;
			return this;
		}
		
		@Override
		public DeliveryAmount build() {
			return new DeliveryAmount.DeliveryAmountImpl(this);
		}
		
		@Override
		public DeliveryAmount.DeliveryAmountBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public DeliveryAmount.DeliveryAmountBuilder prune() {
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getStandardElection()!=null) return true;
			if (getCustomElection()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public DeliveryAmount.DeliveryAmountBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			DeliveryAmount.DeliveryAmountBuilder o = (DeliveryAmount.DeliveryAmountBuilder) other;
			
			
			merger.mergeBasic(getStandardElection(), o.getStandardElection(), this::setStandardElection);
			merger.mergeBasic(getCustomElection(), o.getCustomElection(), this::setCustomElection);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			DeliveryAmount _that = getType().cast(o);
		
			if (!Objects.equals(standardElection, _that.getStandardElection())) return false;
			if (!Objects.equals(customElection, _that.getCustomElection())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (standardElection != null ? standardElection.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (customElection != null ? customElection.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "DeliveryAmountBuilder {" +
				"standardElection=" + this.standardElection + ", " +
				"customElection=" + this.customElection +
			'}';
		}
	}
}
