package cdm.event.common;

import cdm.event.common.CallTypeEnum;
import cdm.event.common.MarginCallInstructionType;
import cdm.event.common.MarginCallInstructionType.MarginCallInstructionTypeBuilder;
import cdm.event.common.MarginCallInstructionType.MarginCallInstructionTypeBuilderImpl;
import cdm.event.common.MarginCallInstructionType.MarginCallInstructionTypeImpl;
import cdm.event.common.meta.MarginCallInstructionTypeMeta;
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
 * Represents enumeration values to specify the call notification type, direction, specific action type.
 * @version ${project.version}
 */
@RosettaDataType(value="MarginCallInstructionType", builder=MarginCallInstructionType.MarginCallInstructionTypeBuilderImpl.class, version="${project.version}")
public interface MarginCallInstructionType extends RosettaModelObject {

	MarginCallInstructionTypeMeta metaData = new MarginCallInstructionTypeMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Indicates the status of the call message type, such as expected call, notification of a call or an actionable margin call.
	 */
	CallTypeEnum getCallType();
	/**
	 * Indicates the choice if the call instruction is visible or not to the other party.
	 */
	Boolean getVisibilityIndicator();

	/*********************** Build Methods  ***********************/
	MarginCallInstructionType build();
	
	MarginCallInstructionType.MarginCallInstructionTypeBuilder toBuilder();
	
	static MarginCallInstructionType.MarginCallInstructionTypeBuilder builder() {
		return new MarginCallInstructionType.MarginCallInstructionTypeBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends MarginCallInstructionType> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends MarginCallInstructionType> getType() {
		return MarginCallInstructionType.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("callType"), CallTypeEnum.class, getCallType(), this);
		processor.processBasic(path.newSubPath("visibilityIndicator"), Boolean.class, getVisibilityIndicator(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface MarginCallInstructionTypeBuilder extends MarginCallInstructionType, RosettaModelObjectBuilder {
		MarginCallInstructionType.MarginCallInstructionTypeBuilder setCallType(CallTypeEnum callType);
		MarginCallInstructionType.MarginCallInstructionTypeBuilder setVisibilityIndicator(Boolean visibilityIndicator);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("callType"), CallTypeEnum.class, getCallType(), this);
			processor.processBasic(path.newSubPath("visibilityIndicator"), Boolean.class, getVisibilityIndicator(), this);
		}
		

		MarginCallInstructionType.MarginCallInstructionTypeBuilder prune();
	}

	/*********************** Immutable Implementation of MarginCallInstructionType  ***********************/
	class MarginCallInstructionTypeImpl implements MarginCallInstructionType {
		private final CallTypeEnum callType;
		private final Boolean visibilityIndicator;
		
		protected MarginCallInstructionTypeImpl(MarginCallInstructionType.MarginCallInstructionTypeBuilder builder) {
			this.callType = builder.getCallType();
			this.visibilityIndicator = builder.getVisibilityIndicator();
		}
		
		@Override
		@RosettaAttribute("callType")
		public CallTypeEnum getCallType() {
			return callType;
		}
		
		@Override
		@RosettaAttribute("visibilityIndicator")
		public Boolean getVisibilityIndicator() {
			return visibilityIndicator;
		}
		
		@Override
		public MarginCallInstructionType build() {
			return this;
		}
		
		@Override
		public MarginCallInstructionType.MarginCallInstructionTypeBuilder toBuilder() {
			MarginCallInstructionType.MarginCallInstructionTypeBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(MarginCallInstructionType.MarginCallInstructionTypeBuilder builder) {
			ofNullable(getCallType()).ifPresent(builder::setCallType);
			ofNullable(getVisibilityIndicator()).ifPresent(builder::setVisibilityIndicator);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			MarginCallInstructionType _that = getType().cast(o);
		
			if (!Objects.equals(callType, _that.getCallType())) return false;
			if (!Objects.equals(visibilityIndicator, _that.getVisibilityIndicator())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (callType != null ? callType.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (visibilityIndicator != null ? visibilityIndicator.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "MarginCallInstructionType {" +
				"callType=" + this.callType + ", " +
				"visibilityIndicator=" + this.visibilityIndicator +
			'}';
		}
	}

	/*********************** Builder Implementation of MarginCallInstructionType  ***********************/
	class MarginCallInstructionTypeBuilderImpl implements MarginCallInstructionType.MarginCallInstructionTypeBuilder {
	
		protected CallTypeEnum callType;
		protected Boolean visibilityIndicator;
	
		public MarginCallInstructionTypeBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("callType")
		public CallTypeEnum getCallType() {
			return callType;
		}
		
		@Override
		@RosettaAttribute("visibilityIndicator")
		public Boolean getVisibilityIndicator() {
			return visibilityIndicator;
		}
		
	
		@Override
		@RosettaAttribute("callType")
		public MarginCallInstructionType.MarginCallInstructionTypeBuilder setCallType(CallTypeEnum callType) {
			this.callType = callType==null?null:callType;
			return this;
		}
		@Override
		@RosettaAttribute("visibilityIndicator")
		public MarginCallInstructionType.MarginCallInstructionTypeBuilder setVisibilityIndicator(Boolean visibilityIndicator) {
			this.visibilityIndicator = visibilityIndicator==null?null:visibilityIndicator;
			return this;
		}
		
		@Override
		public MarginCallInstructionType build() {
			return new MarginCallInstructionType.MarginCallInstructionTypeImpl(this);
		}
		
		@Override
		public MarginCallInstructionType.MarginCallInstructionTypeBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public MarginCallInstructionType.MarginCallInstructionTypeBuilder prune() {
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getCallType()!=null) return true;
			if (getVisibilityIndicator()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public MarginCallInstructionType.MarginCallInstructionTypeBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			MarginCallInstructionType.MarginCallInstructionTypeBuilder o = (MarginCallInstructionType.MarginCallInstructionTypeBuilder) other;
			
			
			merger.mergeBasic(getCallType(), o.getCallType(), this::setCallType);
			merger.mergeBasic(getVisibilityIndicator(), o.getVisibilityIndicator(), this::setVisibilityIndicator);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			MarginCallInstructionType _that = getType().cast(o);
		
			if (!Objects.equals(callType, _that.getCallType())) return false;
			if (!Objects.equals(visibilityIndicator, _that.getVisibilityIndicator())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (callType != null ? callType.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (visibilityIndicator != null ? visibilityIndicator.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "MarginCallInstructionTypeBuilder {" +
				"callType=" + this.callType + ", " +
				"visibilityIndicator=" + this.visibilityIndicator +
			'}';
		}
	}
}
